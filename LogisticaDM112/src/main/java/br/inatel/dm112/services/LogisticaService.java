package br.inatel.dm112.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.dm112.client.BilletClient;
import br.inatel.dm112.client.EmailClient;
import br.inatel.dm112.client.OrderClient;
import br.inatel.dm112.model.BilletGenResponse;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.PaymentStatus;
import br.inatel.dm112.model.PaymentStatus.PAY_STATUS;
import br.inatel.dm112.model.EntregaStatus;

@Service
public class LogisticaService {  

	@Autowired
	private OrderClient clientOrder;

	@Autowired
	private BilletClient clientBillet;

	@Autowired
	private EmailClient clientEmail;

	public PaymentStatus startPaymentOfOrder(String cpf, int orderNumber) {

		Order order = getOrder(cpf, orderNumber); // (1)

		if (order.getStatus() != Order.STATUS.FILLED.ordinal()) {
			String msg = "Status do pedido " + orderNumber + " inválido: " + order.getStatus();
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.WRONG_ORDER_STATUS);
		}

		try {
			clientOrder.startOrderPayment(orderNumber); // (2)
		} catch (Exception e) {
			String msg = "Erro no serviço de pedido: start payment: " + e.getMessage();
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.ORDER_ERROR);
		}

		BilletGenResponse respBillet;
		try {
			respBillet = clientBillet.callGenerateBilletService(orderNumber, cpf); // (3)
		} catch (Exception e) {
			String msg = "Erro no serviço de boleto";
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.BILLET_ERROR);
		}

		byte[] PDFContent = respBillet.getPdfContent();
		try {
			String email = "destino@exemplo.com"; // Defina um email de destino válido
			clientEmail.enviarEmail(email, PDFContent); // (4)
		} catch (Exception e) {
			String msg = "Erro no serviço de email";
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.EMAIL_ERROR);
		}

		System.out.println("Sucesso ao inicializar o pagamento: orderNumber: " + orderNumber + " cpf: " + cpf);
		return new PaymentStatus(PAY_STATUS.OK.ordinal(), cpf, orderNumber); // (5)
	}

	public PaymentStatus confirmPaymentOfOrder(String cpf, int orderNumber) {
		Order order = getOrder(cpf, orderNumber);

		if (order.getStatus() != Order.STATUS.PENDING.ordinal()) {
			String msg = "Status do pedido " + orderNumber + " inválido: " + order.getStatus();
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.WRONG_ORDER_STATUS);
		}
		try {
			clientOrder.confirmOrderPayment(orderNumber);
		} catch (Exception e) {
			String msg = "Erro no serviço de pedido: confirm payment";
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.ORDER_ERROR);
		}
		System.out.println("Sucesso ao confirmar o pagamento: orderNumber: " + orderNumber + " cpf: " + cpf);
		return new PaymentStatus(PAY_STATUS.OK.ordinal(), cpf, orderNumber);
	}

	private Order getOrder(String cpf, int orderNumber) {
		if (cpf == null || orderNumber < 0) {
			throw PaymentStatus.createErrorStatus("CPF e pedido são obrigatórios", cpf, orderNumber, PAY_STATUS.NULL_VALUES);
		}
		try {
			return clientOrder.retrieveOrder(orderNumber);
		} catch (Exception e) {
			String msg = "Pedido " + orderNumber + " não encontrado.";
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.ORDER_NOT_FOUND);
		}
	}

	public EntregaStatus EntregaStatus(String cpf, int orderNumber) {
		System.out.println("Confirmação de entrega para CPF " + cpf + " e pedido " + orderNumber);
		return new EntregaStatus(EntregaStatus.ENTREGA_STATUS.SUCESSO.ordinal(), cpf, orderNumber);
	}
}

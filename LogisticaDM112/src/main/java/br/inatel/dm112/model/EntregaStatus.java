package br.inatel.dm112.model;

public class EntregaStatus {

    public enum ENTREGA_STATUS { SUCESSO, VALORES_NULOS, PEDIDO_NAO_ENCONTRADO, ESTADO_INVALIDO, ERRO_PEDIDO, ERRO_EMAIL }

    private String cpf;
    private int orderNumber;
    private int status;

    public EntregaStatus(int status, String cpf, int orderNumber) {
        this.status = status;
        this.cpf = cpf;
        this.orderNumber = orderNumber;
    }

    public static RuntimeException createErrorStatus(String msg, String cpf, int orderNumber, ENTREGA_STATUS errorStatus) {
        return new RuntimeException(msg + " | Erro: " + errorStatus.name() + " | CPF: " + cpf + " | Pedido: " + orderNumber);
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public int getOrderNumber() { return orderNumber; }
    public void setOrderNumber(int orderNumber) { this.orderNumber = orderNumber; }
}

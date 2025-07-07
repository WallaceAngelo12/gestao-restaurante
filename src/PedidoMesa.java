package src;

public class PedidoMesa extends Pedido{
    private Mesa mesa;

    public PedidoMesa(String codigoPedido, Mesa mesa){
        super(codigoPedido);
        this.mesa = mesa;
    }

   public Mesa getMesa() {
        return mesa;
   }

    public String getLocalizacao(){
        return "mesa número: " + mesa.getNumero();
    }
}

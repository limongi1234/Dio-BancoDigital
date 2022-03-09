package enumm;

public enum AgenciaTipoEnum 
{
    AGENCIA_0123("0123", 1),
    AGENCIA_0321("0321", 2);

    private final String tipo;
    private final int id;

    AgenciaTipoEnum(String tipo, Integer id) 
    {
        this.tipo = tipo;
        this.id = id;
    }

    public String getTipo() 
    {
        return this.tipo;
    }

    public int getId() 
    {
        return this.id;
    }
}

package exceptions;

public class UsuarioException extends Exception 
{

    private static final long serialVersionUID = 1L;

    public UsuarioException() 
    {
        super();
    }

    public UsuarioException(String mensagem) 
    {
        super(mensagem);
    }

    public UsuarioException(String mensagem, Throwable causa) 
    {
        super(mensagem, causa);
    }

    public UsuarioException(Throwable causa) 
    {
        super(causa);
    }


}

package tk.govfundme.models.exceptions;

public class DuplicateProjectNameException extends Exception
{
    public DuplicateProjectNameException() { super(); }
    public DuplicateProjectNameException(String message) { super(message); }
    public DuplicateProjectNameException(String message, Throwable cause) { super(message, cause); }
    public DuplicateProjectNameException(Throwable cause) { super(cause); }
}

package beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SampleBean
{
    private String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String redirect(String message)
    {
        this.message = message;
        return "sample";
    }
}

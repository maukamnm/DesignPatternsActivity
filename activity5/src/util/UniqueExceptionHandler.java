package util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class UniqueExceptionHandler extends ExceptionHandlerWrapper
{
	private ExceptionHandler wrapped;

	UniqueExceptionHandler(ExceptionHandler exception)
	{
		this.wrapped = exception;
	}

	@Override
	public ExceptionHandler getWrapped()
	{
		return wrapped;
	}

	@Override
    public void handle() throws FacesException
    {
        Iterator<ExceptionQueuedEvent> queue = getUnhandledExceptionQueuedEvents().iterator();

        while (queue.hasNext())
        {
            ExceptionQueuedEvent qEvent = queue.next();
            ExceptionQueuedEventContext exceptionQueuedEventContext = (ExceptionQueuedEventContext)qEvent.getSource();
            try 
            {
                Throwable throwable = exceptionQueuedEventContext.getException();
                
                StringWriter writer = new StringWriter();
                PrintWriter pWriter = new PrintWriter(writer);
                throwable.printStackTrace(pWriter);

                FacesContext context = FacesContext.getCurrentInstance();
                Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
                NavigationHandler nav = context.getApplication().getNavigationHandler();
                requestMap.put("error-message", throwable.getMessage());
                requestMap.put("error-stack", writer.toString());
                nav.handleNavigation(context, null, "Error.xhtml");
                context.renderResponse();
            } 
            finally 
            {
                queue.remove();
            }
        }
	}
}
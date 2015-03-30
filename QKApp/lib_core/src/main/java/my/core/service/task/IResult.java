package my.core.service.task;

/**
 * 
 * @author hermeschang
 *
 */
public interface IResult {

	public boolean isException();
	
	/**
	 * 
	 * @return if <isException> return a DJException Object, otherwise return the result.
	 */
	public Object getResult();
}

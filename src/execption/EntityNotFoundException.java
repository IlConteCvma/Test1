package execption;

public class EntityNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException() {
		super();
	}
	
	public EntityNotFoundException(String entity) {
		super(entity + "not found");
		printStackTrace();
	}
}

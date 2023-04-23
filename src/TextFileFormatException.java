class TextFileFormatException extends IndexOutOfBoundsException
{
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Parameterless Constructor
      public TextFileFormatException() {}

      // Constructor that accepts a message
      public TextFileFormatException(String message)
      {
         super(message);
      }
 }
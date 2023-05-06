package A3;
class TextFileFormatException extends IndexOutOfBoundsException
{
	private static final long serialVersionUID = 1L;

      public TextFileFormatException() {}

      public TextFileFormatException(String message)
      {
         super(message);
      }
 }
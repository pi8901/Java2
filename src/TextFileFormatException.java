import java.io.IOException;

class TextFileFormatException extends IOException
{
      // Parameterless Constructor
      public TextFileFormatException() {}

      // Constructor that accepts a message
      public TextFileFormatException(String message)
      {
         super(message);
      }
 }
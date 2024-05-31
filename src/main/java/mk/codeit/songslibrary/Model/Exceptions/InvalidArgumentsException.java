package mk.codeit.songslibrary.Model.Exceptions;

public class InvalidArgumentsException extends  RuntimeException{
    public InvalidArgumentsException(){
        super("Cannot invoke methods due to invalid arguments!");
    }
}

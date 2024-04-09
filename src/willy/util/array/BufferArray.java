package willy.util.array;

public interface BufferArray<E> {
    public void defragment();
    //Moves all the elements to the left.
    public boolean dimension(int newDimension);
    //Resizes the array to the specified dimension. If the specified dimension is less than the current dimension, the array is truncated.
}

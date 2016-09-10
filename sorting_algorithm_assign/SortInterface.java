public interface SortInterface
{
    public void insertionSort(Comparable[] array, int lowindex, int highindex, boolean reversed);
    public void selectionSort(Comparable[] array, int lowindex, int highindex, boolean reversed);
    public void shellSort(Comparable[] array, int lowindex, int highindex, boolean reversed);
    public void bucketSort(int[] array, int lowindex, int highindex, boolean reversed);
    public void heapSort(Comparable[] array, int lowindex, int highindex, boolean reversed);
    public void quicksort(Comparable[] array, int lowindex, int highindex, boolean reversed);
    public void radixSort(int [] array, int lowindex, int highindex, boolean reversed);
    public LLNode selectionSortLL(LLNode list, boolean reversed);
    public LLNode mergeSortLL(LLNode list, boolean reversed);
    public LLNode insertionSortLL(LLNode list, boolean reversed);
    public void optimizedQuicksort(Comparable[] array, int lowindex, int highindex, boolean reversed);
}
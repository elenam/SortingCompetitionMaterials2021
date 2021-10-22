public class QuickSort<T extends Comparable<T>> {

        public static <T extends Comparable<T>> void quicksort(T[] array, int a, int b, java.util.Comparator<? super T> c) {
                 if (a < b) {
                        int i = a, j = b;
                        T x = array[(i + j) / 2];

                        do {
                                while (array[i].compareTo(x) < 0)
                                        i++;
                                while (x.compareTo(array[j]) < 0)
                                        j--;

                                if (i <= j) {
                                        T tmp = array[i];
                                        array[i] = array[j];
                                        array[j] = tmp;
                                        i++;
                                        j--;
                                }

                        } while (i <= j);

                        quicksort(array, a, j, c);
                        quicksort(array, i, b, c);
                }
        }

}
import java.util.Random;
public class QuickSort<T extends Comparable<T>> {

public static <T extends Comparable<T>> void quicksort(T[] array, int a, int b) {
        if (a < b) {
        int i = a, j = b;
        T x = array[(i + j) / 2];

        do {
        while (array[i].compareTo(x) < 0) i++;
        while (x.compareTo(array[j]) < 0) j--;

        if ( i <= j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        i++;
        j--;
        }

        } while (i <= j);

        quicksort(array, a, j);
        quicksort(array, i, b);
        }
        }

public static void main(String[] args) {
        Integer[] integerarray = {50, 55, 11, 89, 90, 1, 20, 11};
        QuickSort<Integer> qsinteger = new QuickSort<Integer>();
        qsinteger.quicksort(integerarray, 0, integerarray.length-1);
        for(Integer i: integerarray) {
        System.out.println(i);
        }
        String[] stringarray = {"bird","moth","apple","zebra","banana","desert","pig"};
        QuickSort<String> qsstring = new QuickSort<String>();
        qsstring.quicksort(stringarray, 0, stringarray.length-1);
        for(String i: stringarray) {
        System.out.println(i);
        }
        Circle[] circlearray = new Circle[20];
        Random rand = new Random();
        for (int index = 0; index < 20; index++)
        {
        circlearray[index] = new Circle();
        circlearray[index].xValue = Math.abs(rand.nextInt()) % 100;
        circlearray[index].yValue = Math.abs(rand.nextInt()) % 100;
        circlearray[index].radius = Math.abs(rand.nextInt()) % 100;

        }
        System.out.println("Circle Array Unsorted....");
        for(int i = 0;i < 20;i++){

        System.out.println(circlearray[i]);
        }
        QuickSort<Circle> qscircle = new QuickSort<Circle>();
        qscircle.quicksort(circlearray, 0, circlearray.length-1);
        System.out.println("Circle Array Sorted");
        for(Circle i: circlearray) {
        System.out.println(i);
        }

    }

}
import java.util.Arrays;
import java.util.Random;

public class MergeSortThreaded {

    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        int[] original = new int[80_000_000];
        for (int i = 0; i < original.length; i++) {
            original[i] = rand.nextInt(10);
        }

        //System.out.println("ИСХОДНЫЙ МАССИВ: " + Arrays.toString(original));

        System.out.println("ПРИМЕР 1: Сортировка масива в ОДИН ПОТОК");
        System.out.println("\n");
        System.out.println("СТАРТ СОРТИРОВКИ");
        int[] test1Array = original.clone();
        long startTime = System.currentTimeMillis();

        Worker runner1 = new Worker(test1Array);
        runner1.start();
        runner1.join();

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("1-thread MergeSort takes: " + (float) elapsedTime / 1000 + " seconds");
        //System.out.println("Конец сортировки "  + Arrays.toString(runner1.getInternal()));
        System.out.println("\n");

        System.out.println("ПРИМЕР 2: Сортировка масива в ДВА ПОТОКА");
        System.out.println("\n");
        System.out.println("СТАРТ СОРТИРОВКИ");

        startTime = System.currentTimeMillis();
        int[] subArr1 = new int[original.length / 2];
        int[] subArr2 = new int[original.length - original.length / 2];
        System.arraycopy(original, 0, subArr1, 0, original.length / 2);
        System.arraycopy(original, original.length / 2, subArr2, 0, original.length - original.length / 2);


        runner1 = new Worker(subArr1);
        Worker runner2 = new Worker(subArr2);
        runner1.start();
        runner2.start();
        runner1.join();
        runner2.join();
        int[] result = finalMerge(runner1.getInternal(), runner2.getInternal());
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("2-thread MergeSort takes: " + (float) elapsedTime / 1000 + " seconds");
        //System.out.println("Конец сортировки " + Arrays.toString(result));
        System.out.println("\n");


        System.out.println("ПРИМЕР 3: Сортировка масива в ЧЕТЫРЕ ПОТОКА");
        System.out.println("\n");
        System.out.println("СТАРТ СОРТИРОВКИ");

        startTime = System.currentTimeMillis();

        int[] subArr1_1 = new int[original.length / 4];
        int[] subArr1_2 = new int[original.length / 4];
        int[] subArr2_1 = new int[original.length / 4];
        int[] subArr2_2 = new int[original.length / 4];

        System.arraycopy(subArr1, 0, subArr1_1, 0, subArr1.length / 2);
        System.arraycopy(subArr1, subArr1.length / 2, subArr1_2, 0, subArr1.length - subArr1.length / 2);
        System.arraycopy(subArr2, 0, subArr2_1, 0, subArr2.length / 2);
        System.arraycopy(subArr2, subArr2.length / 2, subArr2_2, 0, subArr2.length - subArr2.length / 2);

        runner1 = new Worker(subArr1_1);
        runner2 = new Worker(subArr1_2);
        Worker runner3 = new Worker(subArr2_1);
        Worker runner4 = new Worker(subArr2_2);

        runner1.start();
        runner2.start();
        runner3.start();
        runner4.start();
        runner1.join();
        runner2.join();
        runner3.join();
        runner4.join();

        int[] first = finalMerge(runner1.getInternal(), runner2.getInternal());
        int[] second = finalMerge(runner3.getInternal(), runner4.getInternal());
        int[] finalResult = finalMerge(first, second);


        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("4-thread MergeSort takes: " + (float) elapsedTime / 1000 + " seconds");
        //System.out.println("Конец сортировки " + Arrays.toString(finalResult));
        System.out.println("\n");

        System.out.println("ПРИМЕР 4: Сортировка масива в ВОСЕМЬ ПОТОКОВ");
        System.out.println("\n");
        System.out.println("СТАРТ СОРТИРОВКИ");

        startTime = System.currentTimeMillis();

        int[] subArr1_1_1 = new int[original.length / 8];
        int[] subArr1_1_2 = new int[original.length / 8];
        int[] subArr1_2_1 = new int[original.length / 8];
        int[] subArr1_2_2 = new int[original.length / 8];
        int[] subArr2_1_1 = new int[original.length / 8];
        int[] subArr2_1_2 = new int[original.length / 8];
        int[] subArr2_2_1 = new int[original.length / 8];
        int[] subArr2_2_2 = new int[original.length / 8];

        System.arraycopy(subArr1_1, 0, subArr1_1_1, 0, subArr1_1.length / 2);
        System.arraycopy(subArr1_1, subArr1_1.length / 2, subArr1_1_2, 0, subArr1_1.length - subArr1_1.length / 2);
        System.arraycopy(subArr1_2, 0, subArr1_2_1, 0, subArr1_2.length / 2);
        System.arraycopy(subArr1_2, subArr1_2_2.length / 2, subArr2_2, 0, subArr1_2.length - subArr1_2.length / 2);
        System.arraycopy(subArr2_1, 0, subArr2_1_1, 0, subArr2_1.length / 2);
        System.arraycopy(subArr2_1, subArr2_1.length / 2, subArr2_1_2, 0, subArr2_1.length - subArr2_1.length / 2);
        System.arraycopy(subArr2_2, 0, subArr2_2_1, 0, subArr2_2.length / 2);
        System.arraycopy(subArr2_2, subArr2_2.length / 2, subArr2_2_2, 0, subArr2_2.length - subArr2_2.length / 2);

        runner1 = new Worker(subArr1_1_1);
        runner2 = new Worker(subArr1_1_2);
        runner3 = new Worker(subArr1_2_1);
        runner4 = new Worker(subArr1_2_2);
        Worker runner5 = new Worker(subArr2_1_1);
        Worker runner6 = new Worker(subArr2_1_2);
        Worker runner7 = new Worker(subArr2_2_1);
        Worker runner8 = new Worker(subArr2_2_2);

        runner1.start();
        runner2.start();
        runner3.start();
        runner4.start();
        runner5.start();
        runner6.start();
        runner7.start();
        runner8.start();
        runner1.join();
        runner2.join();
        runner3.join();
        runner4.join();
        runner5.join();
        runner6.join();
        runner7.join();
        runner8.join();

        int[] result1 = finalMerge(runner1.getInternal(), runner2.getInternal());
        int[] result2 = finalMerge(runner3.getInternal(), runner4.getInternal());
        int[] result3 = finalMerge(runner5.getInternal(), runner6.getInternal());
        int[] result4 = finalMerge(runner7.getInternal(), runner8.getInternal());


        int[] finalResult1 = finalMerge(result1, result2);
        int[] finalResult2 = finalMerge(result3, result4);

        int[] finalValue = finalMerge(finalResult1, finalResult2);

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("8-thread MergeSort takes: " + (float) elapsedTime / 1000 + " seconds");
        //System.out.println("Конец сортировки " + Arrays.toString(finalResult));
        System.out.println("\n");

    }

    public static int[] finalMerge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int r = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[r] = a[i];
                i++;
                r++;
            } else {
                result[r] = b[j];
                j++;
                r++;
            }
            if (i == a.length) {
                while (j < b.length) {
                    result[r] = b[j];
                    r++;
                    j++;
                }
            }
            if (j == b.length) {
                while (i < a.length) {
                    result[r] = a[i];
                    r++;
                    i++;
                }
            }
        }
        return result;
    }

}

class Worker extends Thread {
    private int[] internal;

    public int[] getInternal() {
        return internal;
    }

    public void mergeSort(int[] array) {
        if (array.length > 1) {
            int[] left = leftHalf(array);
            int[] right = rightHalf(array);

            mergeSort(left);
            mergeSort(right);

            merge(array, left, right);
        }
    }

    public int[] leftHalf(int[] array) {
        int size1 = array.length / 2;
        int[] left = new int[size1];
        for (int i = 0; i < size1; i++) {
            left[i] = array[i];
        }
        return left;
    }

    public int[] rightHalf(int[] array) {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        int[] right = new int[size2];
        for (int i = 0; i < size2; i++) {
            right[i] = array[i + size1];
        }
        return right;
    }

    public void merge(int[] result, int[] left, int[] right) {
        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i < result.length; i++) {
            if (i2 >= right.length || (i1 < left.length && left[i1] <= right[i2])) {
                result[i] = left[i1];
                i1++;
            } else {
                result[i] = right[i2];
                i2++;
            }
        }
    }

    Worker(int[] arr) {
        internal = arr;
    }

    public void run() {
        mergeSort(internal);
    }
}
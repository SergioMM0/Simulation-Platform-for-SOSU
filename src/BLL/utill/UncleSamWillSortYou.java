package BLL.utill;


import java.util.Collections;
import java.util.List;

public class UncleSamWillSortYou {

    public void maxHeap(List<Integer> array, int index, int size) {

        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && array.get(left) > array.get(largest)) {
            largest = left;
        }
        if (right < size && array.get(right) > array.get(largest)) {
            largest = right;
        }

        if (largest != index) {
            Collections.swap(array, index, largest);
            maxHeap(array, largest, size);
        }

    }

}

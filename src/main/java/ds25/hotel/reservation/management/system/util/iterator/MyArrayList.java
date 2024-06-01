package ds25.hotel.reservation.management.system.util.iterator;

import ds25.hotel.reservation.management.system.util.strategy.Comparator;
import java.util.Arrays;
import java.util.NoSuchElementException;
import lombok.NonNull;

// Aggregate<T> 와 같이 제네릭을 사용하도록 수정
public class MyArrayList<T> implements Aggregate<T> {
	private T[] data;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;

	public MyArrayList() {
		this.data = (T[]) new Object[DEFAULT_CAPACITY];
		this.size = 0;
	}

	public void add(T element) {
		if (size == data.length) {
			resize();
		}
		data[size++] = element;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		return (T) data[index];
	}

	public int size() {
		return this.size;
	}

	private void resize() {
		int newCapacity = data.length * 2;
		data = Arrays.copyOf(data, newCapacity);
	}

	/**
	 * Array 변환
	 *
	 * @return Array
	 */
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[size];
		System.arraycopy(data, 0, result, 0, size);
		return result;
	}


	@SuppressWarnings("unchecked")
	public void sort(@NonNull Comparator<T> sortStrategy) {
		// Bubble Sort
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				if (sortStrategy.compare((T) data[j], (T) data[j + 1]) > 0) {
					Object temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = (T) temp;
				}
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new MyArrayListIterator();
	}


	private class MyArrayListIterator implements Iterator<T> {
		private int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		@SuppressWarnings("unchecked")
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return (T) data[cursor++];
		}

		@Override
		public boolean hasPrevious() {
			return cursor > 0;
		}

		@Override
		@SuppressWarnings("unchecked")
		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			// --cursor는 전위 연산자이므로 먼저 감소시킨 후 배열에 접근합니다.
			return (T) data[--cursor];
		}
	}
}

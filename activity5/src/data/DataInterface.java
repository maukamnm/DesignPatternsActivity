package data;

import beans.Album;

public interface DataInterface<T> {
	T create(T t);
	T findBy(T t);
}

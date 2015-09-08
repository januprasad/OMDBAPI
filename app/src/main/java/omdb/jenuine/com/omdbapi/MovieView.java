package omdb.jenuine.com.omdbapi;

public interface MovieView<T> extends BaseView {
    void addItem(T item);
    void addItems(T[] items);
    void clearItem();
}

package omdb.jenuine.com.omdbapi;

public interface MovieView<T> extends BaseView {
    void addItem(T item);
    void clearItem();
}

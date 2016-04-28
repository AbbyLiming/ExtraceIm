package extrace.net;


public interface IDataAdapter<T> {
	T getData();
	void setData(T data);
	void notifyDataSetChanged();
}

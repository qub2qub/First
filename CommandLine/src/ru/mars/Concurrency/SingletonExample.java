package ru.mars.Concurrency;

public class SingletonExample {

	static SingletonExample _INSTANCE;

	private SingletonExample() {
		super();
	}

	public static SingletonExample getInstance() {
		if (_INSTANCE == null) {
			synchronized (SingletonExample.class) {
				// double checked locking - because second check
				// of Singleton instance with lock
				if (_INSTANCE == null) {
					_INSTANCE = new SingletonExample();
				}
			}
		}
		return _INSTANCE;
	}

}

package com.aidl.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.aidl.client.Book;
import com.aidl.client.BookManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzp on 2016/10/19.
 */

public class AIDLService extends Service
{
    private static final String TAG = "AIDLService";

    private List<Book> mBooks = new ArrayList<>();

    private final BookManager.Stub mBookManager = new BookManager.Stub()
    {
        @Override
        public List<Book> getBooks() throws RemoteException
        {
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException
        {
            if (null != book)
            {
                Log.e(TAG, book.toString());
                mBooks.add(book);
            }
        }

        @Override
        public void updatePrice(int price) throws RemoteException
        {
            for (Book book : mBooks)
            {
                book.setPrice(book.getPrice() + price);
            }
        }
    };

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return mBookManager;
    }
}

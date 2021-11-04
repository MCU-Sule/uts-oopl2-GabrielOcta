package com.example.squiddemo.dao;

import java.util.List;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public interface DaoInterface<T> {
    public List<T> showAllData();
    public  int addData(T data);
    public  int delData(T data);
    public  int updateData(T data);

}

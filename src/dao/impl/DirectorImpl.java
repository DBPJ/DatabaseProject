package dao.impl;

import dao.IDirectorDao;
import entity.Director;

import java.util.List;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class DirectorImpl implements IDirectorDao {
    @Override
    public boolean addDirector(Director director) {
        return false;
    }

    @Override
    public boolean deleteDirector(String number) {
        return false;
    }

    @Override
    public boolean updateDirector(String number, Director director) {
        return false;
    }

    @Override
    public Director queryDirector(String number) {
        return null;
    }

    @Override
    public List<Director> queryDirectors() {
        return null;
    }
}

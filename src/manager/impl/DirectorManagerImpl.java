package manager.impl;

import dao.impl.DirectorDaoImpl;
import entity.Director;
import manager.IDirectorManager;

import java.util.ArrayList;

/**
 * Created by Jindiwei on 2016/12/17.
 */
public class DirectorManagerImpl implements IDirectorManager {
    DirectorDaoImpl directorDao = new DirectorDaoImpl();
    @Override
    public boolean addDirector(Director director) {
        return directorDao.addDirector(director);
    }

    @Override
    public boolean deleteDirector(String number) {
        return directorDao.deleteDirector(number);
    }

    @Override
    public boolean updateDirector(String number, Director director) {
        return directorDao.updateDirector(number, director);
    }

    @Override
    public Director queryDirector(String number) {
        return directorDao.queryDirector(number);
    }

    @Override
    public ArrayList<Director> queryDirectors() {
        return directorDao.queryDirectors();
    }
}

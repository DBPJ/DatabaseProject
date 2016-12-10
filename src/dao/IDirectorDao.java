package dao;

import entity.Director;

import java.util.List;

/**
 * Created by alex on 10/12/2016.
 */
public interface IDirectorDao {
    /**
     * @param director director
     * @return
     */
    public boolean addDirector(Director director);

    /**
     * delete director according to the number
     * @param number number to identify the director
     * @return
     */
    public boolean deleteDirector(String number);

    /**
     * update info, but there is no need to implement this
     * @param number number to identify the director
     * @param director director
     * @return
     */
    public boolean updateDirector(String number, Director director);


    /**
     *
     * @param number number to identify the director
     * @return the user with this number
     */
    public Director queryDirector(String number);

    /**
     * @return all director
     */
    public List<Director> queryDirectors();
}

package org.andy.jpa.dao;

import org.andy.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by jh on 2017/8/12.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find by name.
     *
     * @param name the name
     * @return the user
     */
    User findByName(String name);

    /**
     * Find by name and user name.
     * 如果参数名为多个字母组成，请首字母大写。勿使用驼峰命名，jpa不识别驼峰
     * @param name the name
     * @param age the age
     * @return the user
     */
    User findByNameAndUsername(String name, Integer age);

    /**
     * Find user.
     * User为@Entity 的名字
     * @param name the name
     * @return the user
     */
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

/*    @Query("select install from DeviceInstallDevDetail devDetail " +
            "inner join devDetail.installNo install " +
            "inner join devDetail.device device on (device.snNumber = :deviceNo " +
            "or device.imeiNumber = :deviceNo or device.iccidNumber = :deviceNo)" +
            "where install.installStaffId = :workerId order by install.opDate desc")
    List<DeviceInstall> findInstallByDeviceNoAndWorker(@Param("deviceNo") String deviceNo,
                                                       @Param("workerId") Long workerId);*/
}
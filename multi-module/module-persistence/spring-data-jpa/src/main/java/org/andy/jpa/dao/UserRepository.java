package org.andy.jpa.dao;

import org.andy.jpa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by jh on 2017/8/12.
 */
@RepositoryRestResource(path = "work")
public interface UserRepository extends JpaRepository<Users, Integer> {


    @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    Users findByNameStartsWith(@Param("name") String name);
    /**
     * Find by name.
     *
     * @param name the name
     * @return the user
     */
    Users findByName(String name);

    /**
     * Find by name and user name.
     * 如果参数名为多个字母组成，请首字母大写。勿使用驼峰命名，jpa不识别驼峰
     * @param name the name
     * @param age the age
     * @return the user
     */
    Users findByNameAndUsername(String name, Integer age);

    /**
     * Find user.
     * User为@Entity 的名字
     * @param name the name
     * @return the user
     */
    @Query("from Users u where u.name=:name")
    Users findUser(@Param("name") String name);

/*    @Query("select install from DeviceInstallDevDetail devDetail " +
            "inner join devDetail.installNo install " +
            "inner join devDetail.device device on (device.snNumber = :deviceNo " +
            "or device.imeiNumber = :deviceNo or device.iccidNumber = :deviceNo)" +
            "where install.installStaffId = :workerId order by install.opDate desc")
    List<DeviceInstall> findInstallByDeviceNoAndWorker(@Param("deviceNo") String deviceNo,
                                                       @Param("workerId") Long workerId);*/
}
package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{encryptedPassword}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insert(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{encryptedPassword} WHERE credentialid = #{credentialid}")
    void update(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = userid")
    List<Credential> getCredentials(Integer userid);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = credentialid")
    void deleteCredential(Integer credentialid);
}

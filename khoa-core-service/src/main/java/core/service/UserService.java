package core.service;

import core.dto.CheckLogin;
import core.dto.UserDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by TuanKul on 11/7/2017.
 */
public interface UserService {
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer userID);
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    CheckLogin checkLogin(String name, String password);
    //void validateImportUser(List<UserImportDTO> userImportDTOS);
    //void saveUserImport(List<UserImportDTO> userImportDTOS);
}

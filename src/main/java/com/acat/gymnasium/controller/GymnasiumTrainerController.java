package com.acat.gymnasium.controller;

import com.acat.gymnasium.constants.RestResult;
import com.acat.gymnasium.controller.bo.GymnasiumFitnessUserBo;
import com.acat.gymnasium.controller.bo.GymnasiumOperatorRecordBo;
import com.acat.gymnasium.controller.bo.GymnasiumTrainerBo;
import com.acat.gymnasium.controller.vo.*;
import com.acat.gymnasium.domain.GymnasiumFitnessUser;
import com.acat.gymnasium.domain.GymnasiumOperatorRecord;
import com.acat.gymnasium.domain.GymnasiumTrainer;
import com.acat.gymnasium.enums.GenderEnum;
import com.acat.gymnasium.enums.OperatorEnum;
import com.acat.gymnasium.enums.RestEnum;
import com.acat.gymnasium.enums.RoleEnum;
import com.acat.gymnasium.service.GymnasiumFitnessUserService;
import com.acat.gymnasium.service.GymnasiumOperatorRecordService;
import com.acat.gymnasium.service.GymnasiumTrainerService;
import com.acat.gymnasium.util.DateUtil;
import com.acat.gymnasium.util.ExcelUtil;
import com.acat.gymnasium.util.MatcherUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gym")
@Slf4j
public class GymnasiumTrainerController {

    @Resource
    private GymnasiumTrainerService gymnasiumTrainerService;
    @Resource
    private GymnasiumFitnessUserService gymnasiumFitnessUserService;
    @Resource
    private GymnasiumOperatorRecordService gymnasiumOperatorRecordService;

    @PostMapping("/login")
    public RestResult<GymnasiumTrainer> login(HttpServletRequest httpServletRequest,
                                         @RequestBody GymnasiumTrainerVo gymnasiumTrainerVo) {
        try {
            if (StringUtils.isBlank(gymnasiumTrainerVo.getUsername())) {
                return new RestResult<>(RestEnum.USERNAME_EMPTY_PARAM);
            }
            if (StringUtils.isBlank(gymnasiumTrainerVo.getPassword())) {
                return new RestResult<>(RestEnum.PASSWORD_EMPTY_PARAM);
            }

            GymnasiumTrainer gymnasiumTrainer = gymnasiumTrainerService.GymnasiumLogin(gymnasiumTrainerVo.getUsername(), gymnasiumTrainerVo.getPassword());
            if (gymnasiumTrainer == null) {
                return new RestResult<>(RestEnum.USER_EMPTY.getCode(), RestEnum.USER_EMPTY.getMsg(), null);
            }
            return new RestResult<>(RestEnum.SUCCESS, gymnasiumTrainer);
        } catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping("/register")
    public RestResult register(HttpServletRequest httpServletRequest,
                                              @RequestBody RegisterVo registerVo) {
        try {
            if (!registerVo.getPassword().equals(registerVo.getRePassword())) {
                return new RestResult<>(RestEnum.PASSWORD_IS_DISGRESS.getCode(), RestEnum.PASSWORD_IS_DISGRESS.getMsg(), null);
            }

            List<GymnasiumTrainer> gymnasiumTrainerList = gymnasiumTrainerService.findGymnasiumTrainerByUsernameEquals(registerVo.getUserName());
            if (gymnasiumTrainerList.size() > 0) {
                return new RestResult<>(RestEnum.USERNAME_IS_REPEAT.getCode(), RestEnum.USERNAME_IS_REPEAT.getMsg(), null);
            }
            gymnasiumTrainerService.saveBean(registerVo);
            return new RestResult<>(RestEnum.SUCCESS, null);
        } catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping("/deleteTrainer")
    public RestResult register(HttpServletRequest httpServletRequest,
                               @RequestParam Integer trainerId) {
        try {
            gymnasiumTrainerService.deleteById(trainerId);
            return new RestResult<>(RestEnum.SUCCESS, "删除成功!!!");
        } catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    /************************************************https://blog.csdn.net/iotjin/article/details/128012388 ******************************************/
    @PostMapping("/getTrainerInfo")
    public RestResult<List<GymnasiumTrainerBo>> getTrainerInfo(HttpServletRequest httpServletRequest,
                                                               @RequestBody TrainerInfoVo trainerInfoVo) {
        try {
            List<GymnasiumTrainer> gymnasiumTrainerList = new ArrayList<>();
            RoleEnum roleEnum = RoleEnum.getRoleEnumByCode(trainerInfoVo.getRoleId());
            if (RoleEnum.GUAN_LI_YUAN == roleEnum) {
                gymnasiumTrainerList = gymnasiumTrainerService.getTrainerInfoFromGly(trainerInfoVo.getRoleId());
            }else {
                gymnasiumTrainerList = gymnasiumTrainerService.getTrainerInfoFromJl(trainerInfoVo.getUserId());
            }

            List<GymnasiumTrainerBo> gymnasiumTrainerBoList = new ArrayList<>();
            for (GymnasiumTrainer gymnasiumTrainer : gymnasiumTrainerList) {
                GymnasiumTrainerBo gymnasiumTrainerBo = new GymnasiumTrainerBo()
                        .setId(gymnasiumTrainer.getId())
                        .setTrainerNickname(gymnasiumTrainer.getTrainerNickname())
                        .setTrainerIntroduction(gymnasiumTrainer.getTrainerIntroduction())
                        .setRole(RoleEnum.getRoleEnumByCode(gymnasiumTrainer.getRole()).getRole());
                gymnasiumTrainerBoList.add(gymnasiumTrainerBo);
            }
            return new RestResult<>(RestEnum.SUCCESS, gymnasiumTrainerBoList);
        }catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    /***************************************************************************************/
    @GetMapping("/getFitnessUserInfo")
    public RestResult<List<GymnasiumFitnessUserBo>> getFitnessUserInfo(HttpServletRequest httpServletRequest, @RequestParam("trainerId") int trainerId) {
        try {
            List<GymnasiumTrainer> gymnasiumTrainerList = gymnasiumTrainerService.getTrainerInfoFromJl(trainerId);
            if (gymnasiumTrainerList == null) {
                return new RestResult<>(RestEnum.USER_EMPTY.getCode(), RestEnum.USER_EMPTY.getMsg(), null);
            }

            String trainerName = gymnasiumTrainerList.get(0).getTrainerNickname();
            List<GymnasiumFitnessUserBo> gymnasiumFitnessUserBoList = new ArrayList<>();
            List<GymnasiumFitnessUser> gymnasiumFitnessUserList = gymnasiumFitnessUserService.getGymnasiumFitnessUsersListByTrainerId(trainerId);
            if (gymnasiumFitnessUserList == null) {
                return new RestResult<>(RestEnum.SUCCESS,  gymnasiumFitnessUserBoList);
            }

            gymnasiumFitnessUserBoList = convertGymnasiumFitnessUserBoList(true, gymnasiumFitnessUserList, trainerName, new HashMap<>());
            return new RestResult<>(RestEnum.SUCCESS, gymnasiumFitnessUserBoList);
        } catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping("/shoppingCourse")
    public RestResult shoppingCourse(HttpServletRequest httpServletRequest,
                                                             @RequestBody ShoppingCourseVo shoppingCourseVo) {
        try {
            String username = shoppingCourseVo.getUserName();
            if(StringUtils.isBlank(username)) {
                return new RestResult<>(RestEnum.USERNAME_EMPTY_PARAM.getCode(), RestEnum.USERNAME_EMPTY_PARAM.getMsg(), null);
            }

            if (!MatcherUtils.isPhoneNum(shoppingCourseVo.getPhone().trim())) {
                return new RestResult<>(RestEnum.PHONE_IS_ERROR.getCode(), RestEnum.PHONE_IS_ERROR.getMsg(), null);
            }

            GymnasiumFitnessUser gymnasiumFitnessUser = gymnasiumFitnessUserService.getGymnasiumFitnessUsersByUsername(username);
            if (gymnasiumFitnessUser != null) {
                return new RestResult<>(RestEnum.USER_IS_EXISTS.getCode(), RestEnum.USER_IS_EXISTS.getMsg(), null);
            }
            gymnasiumFitnessUserService.shoppingCourse(shoppingCourseVo);
        }catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
        return new RestResult<>(RestEnum.SUCCESS.getCode(), "会员添加成功！！！");
    }

    /**
     * 转课
     * @param httpServletRequest
     * @param transferFitnessUserVo
     * @return
     */
    @PostMapping("/transferFitnessUser")
    public RestResult transferFitnessUser(HttpServletRequest httpServletRequest,
                                     @RequestBody TransferFitnessUserVo transferFitnessUserVo) {

        try {
            Integer beforeTrainerId = transferFitnessUserVo.getBeforeTrainerId();
            Integer afterTrainerId = transferFitnessUserVo.getAfterTrainerId();

            List<GymnasiumFitnessUser> gymnasiumFitnessUserList = gymnasiumFitnessUserService.getGymnasiumFitnessUsersListByTrainerId(beforeTrainerId);
            if (gymnasiumFitnessUserList.size() == 0) {
                return new RestResult<>(RestEnum.HUI_YUAN_IS_EMPTY.getCode(), RestEnum.HUI_YUAN_IS_EMPTY.getMsg(), null);
            }
            gymnasiumFitnessUserService.transferFitnessUser(afterTrainerId, gymnasiumFitnessUserList);
            return new RestResult<>(RestEnum.SUCCESS);
        }catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }


    /*************************************用户操作*******************************************************************************/
    @PostMapping("/operatorCourse")
    public RestResult operatorCourse(HttpServletRequest httpServletRequest, @RequestBody OperatorCourseVo operatorCourseVo) {

        try {
            Integer userId = operatorCourseVo.getUserId();
            Integer operator = operatorCourseVo.getOperatorId();
            Integer keCourse = operatorCourseVo.getKeCourse();

            OperatorEnum operatorEnum = OperatorEnum.getOperatorEnumMap(operator);
            if (operatorEnum == null) {
                return new RestResult<>(RestEnum.PLEASE_TRY.getCode(), RestEnum.PLEASE_TRY.getMsg(), null);
            }

            if (OperatorEnum.XU_KE == operatorEnum && keCourse <= 0) {
                return new RestResult<>(RestEnum.XUKE_COURSE_DAYU_0.getCode(), RestEnum.XUKE_COURSE_DAYU_0.getMsg(), null);
            }
            return gymnasiumFitnessUserService.operatorCourse(userId, operatorEnum, keCourse);
        }catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping("/getOperatorInfo")
    public RestResult<List<GymnasiumOperatorRecordBo>> getOperatorInfo(HttpServletRequest httpServletRequest, @RequestParam("userId") int userId) {
        try {

            GymnasiumFitnessUser gymnasiumFitnessUser = gymnasiumFitnessUserService.getGymnasiumFitnessUsersByUserId(userId);
            if (gymnasiumFitnessUser == null) {
                return new RestResult<>(RestEnum.USER_IS_EMPTY.getCode(), RestEnum.USER_IS_EMPTY.getMsg(), null);
            }

            List<GymnasiumOperatorRecord> gymnasiumOperatorRecordList = gymnasiumOperatorRecordService.getGymnasiumOperatorRecordList(userId);
            List<GymnasiumOperatorRecordBo> gymnasiumOperatorRecordBoList = convertGymnasiumOperatorRecordBoList(true, gymnasiumOperatorRecordList, gymnasiumFitnessUser.getUsername(), new HashMap<>());
            return new RestResult<>(RestEnum.SUCCESS, gymnasiumOperatorRecordBoList);
        } catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping("/downloadFitnessUserExcel")
    public RestResult downloadFitnessUserExcel(HttpServletRequest httpServletRequest) {

        try {
            List<GymnasiumTrainer> gymnasiumTrainerList = gymnasiumTrainerService.getTrainerInfoFromGly(1);
            if (gymnasiumTrainerList == null) {
                return new RestResult<>(RestEnum.USER_EMPTY.getCode(), RestEnum.USER_EMPTY.getMsg(), null);
            }

            Map<Integer, String> trainerMap = new HashMap<>();
            gymnasiumTrainerList.forEach(e -> {
                trainerMap.put(e.getId(), e.getTrainerNickname());
            });

            List<GymnasiumFitnessUser> gymnasiumFitnessUserList = gymnasiumFitnessUserService.findGymnasiumFitnessUsersByTrainerIdIn(trainerMap.keySet().stream().collect(Collectors.toList()));
            if (gymnasiumFitnessUserList == null) {
                return new RestResult<>(RestEnum.HUI_YUAN_IS_EMPTY.getCode(), RestEnum.HUI_YUAN_IS_EMPTY.getMsg(), null);
            }

            List<GymnasiumFitnessUserBo> gymnasiumFitnessUserBoList = convertGymnasiumFitnessUserBoList(false, gymnasiumFitnessUserList, "", trainerMap);
            Map<String, List<GymnasiumFitnessUserBo>> gymnasiumFitnessUserBoListListMap = gymnasiumFitnessUserBoList.stream().collect(Collectors.groupingBy((GymnasiumFitnessUserBo g) -> g.getTrainerName()));

            String filePath = "D:\\vueProject\\" + "fitness_" + String.valueOf(new Date().getTime()) + ".xls";
            ExcelUtil.convertExcelByMapByFitnessUser(gymnasiumFitnessUserBoListListMap, filePath);
            return new RestResult<>(RestEnum.SUCCESS, "下载成功,路径:" + filePath);
        }catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping("/downloadOperatorExcel")
    public RestResult downloadOperatorExcel(HttpServletRequest httpServletRequest, @RequestParam("trainerId") int trainerId) {

        try {
            List<GymnasiumTrainer> gymnasiumTrainerList = gymnasiumTrainerService.getTrainerInfoFromJl(trainerId);
            if (gymnasiumTrainerList == null) {
                return new RestResult<>(RestEnum.USER_EMPTY.getCode(), RestEnum.USER_EMPTY.getMsg(), null);
            }

            List<GymnasiumFitnessUser> gymnasiumFitnessUserList = gymnasiumFitnessUserService.getGymnasiumFitnessUsersListByTrainerId(trainerId);
            if (gymnasiumFitnessUserList == null) {
                return new RestResult<>(RestEnum.HUI_YUAN_IS_EMPTY.getCode(), RestEnum.HUI_YUAN_IS_EMPTY.getMsg(), null);
            }

            Map<Integer, String> userMap = new HashMap<>();
            gymnasiumFitnessUserList.forEach(e -> {
                userMap.put(e.getId(), e.getUsername());
            });

            List<GymnasiumOperatorRecord> gymnasiumOperatorRecordList = gymnasiumOperatorRecordService.findGymnasiumOperatorRecordsByUserIdList(userMap.keySet().stream().collect(Collectors.toList()));
            if (gymnasiumOperatorRecordList == null) {
                return new RestResult<>(RestEnum.HUI_YUAN_OPERATOR_IS_EMPTY.getCode(), RestEnum.HUI_YUAN_OPERATOR_IS_EMPTY.getMsg(), null);
            }

            List<GymnasiumOperatorRecordBo> gymnasiumOperatorRecordBoList = convertGymnasiumOperatorRecordBoList(false, gymnasiumOperatorRecordList, "", userMap);
            Map<String, List<GymnasiumOperatorRecordBo>> gymnasiumOperatorRecordBoListMap = gymnasiumOperatorRecordBoList.stream().collect(Collectors.groupingBy((GymnasiumOperatorRecordBo g) -> g.getUserName()));

            String filePath = "D:\\vueProject\\" + "operator_" + String.valueOf(new Date().getTime()) + ".xls";
            ExcelUtil.convertExcelByMapByOperator(gymnasiumOperatorRecordBoListMap, filePath);
            return new RestResult<>(RestEnum.SUCCESS, "下载成功,路径:" + filePath);
        }catch (Exception e) {
            return new RestResult<>(RestEnum.FAILED.getCode(), e.getMessage(), null);
        }
    }

    /**
     * 封装bean
     * @param gymnasiumOperatorRecordList
     * @param userName
     * @return
     */
    private List<GymnasiumOperatorRecordBo> convertGymnasiumOperatorRecordBoList(boolean flag, List<GymnasiumOperatorRecord> gymnasiumOperatorRecordList, String userName, Map<Integer, String> userMap) {
        List<GymnasiumOperatorRecordBo> gymnasiumOperatorRecordBoList = new ArrayList<>();

        if (gymnasiumOperatorRecordList != null) {
            for (GymnasiumOperatorRecord gymnasiumOperatorRecord : gymnasiumOperatorRecordList) {
                GymnasiumOperatorRecordBo gymnasiumOperatorRecordBo = new GymnasiumOperatorRecordBo()
                    .setId(gymnasiumOperatorRecord.getId())
                    .setOperator(OperatorEnum.getOperatorEnumMap(gymnasiumOperatorRecord.getOperator()).getValue())
                    .setUserName(flag ? userName : userMap.get(gymnasiumOperatorRecord.getUserId()))
                    .setCreateTime(DateUtil.dateToStr(gymnasiumOperatorRecord.getCreateTime()))
                    .setUpdateTime(DateUtil.dateToStr(gymnasiumOperatorRecord.getUpdateTime()));
                gymnasiumOperatorRecordBoList.add(gymnasiumOperatorRecordBo);
            }
        }
        return gymnasiumOperatorRecordBoList;
    }

    /**
     *
     * @param flag
     * @param gymnasiumFitnessUserList
     * @param trainerName
     * @param trainerMap
     * @return
     */
    private List<GymnasiumFitnessUserBo> convertGymnasiumFitnessUserBoList(boolean flag, List<GymnasiumFitnessUser> gymnasiumFitnessUserList, String trainerName, Map<Integer, String> trainerMap) {
        List<GymnasiumFitnessUserBo> gymnasiumFitnessUserBoList = new ArrayList<>();

        for (GymnasiumFitnessUser gymnasiumFitnessUser : gymnasiumFitnessUserList) {
            GymnasiumFitnessUserBo gymnasiumFitnessUserBo = new GymnasiumFitnessUserBo()
                .setId(gymnasiumFitnessUser.getId())
                .setGender(GenderEnum.getGenderEnumByCode(gymnasiumFitnessUser.getGender()).getGenderValue())
                .setPhone(gymnasiumFitnessUser.getPhone())
                .setUsername(gymnasiumFitnessUser.getUsername())
                .setCurrentCourse(gymnasiumFitnessUser.getCurrentCourse())
                .setTrainerId(gymnasiumFitnessUser.getTrainerId())
                .setTrainerName(flag ? trainerName : trainerMap.get(gymnasiumFitnessUser.getTrainerId()))
                .setCreateTime(DateUtil.dateToStr(gymnasiumFitnessUser.getCreateTime()))
                .setUpdateTime(DateUtil.dateToStr(gymnasiumFitnessUser.getUpdateTime()));
            gymnasiumFitnessUserBoList.add(gymnasiumFitnessUserBo);
        }
        return gymnasiumFitnessUserBoList;
    }
}

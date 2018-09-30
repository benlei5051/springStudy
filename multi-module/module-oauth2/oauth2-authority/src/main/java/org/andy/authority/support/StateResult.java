package org.andy.authority.support;

/**
 * @author sean
 * @date 2017/11/7
 */

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sean
 * @date 2017/10/20
 */
@Getter
@Setter
public class StateResult {
    protected String code;
    protected String description;

    public static StateResult success() {
        return new StateResult(ResultEnum.SUCCESS);
    }

    public static StateResult error(ResultEnum resultEnum) {
        return new StateResult(resultEnum);
    }


    public StateResult(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public StateResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.description = resultEnum.getMessage();
    }

    public StateResult() {
    }
}

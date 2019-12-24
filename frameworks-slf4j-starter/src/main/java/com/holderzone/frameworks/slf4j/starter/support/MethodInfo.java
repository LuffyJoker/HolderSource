package com.holderzone.frameworks.slf4j.starter.support;

import java.util.List;

public class MethodInfo {
    private String classAllName;
    private String classSimpleName;
    private String methodName;
    private List<String> paramNames;
    private Integer lineNumber;

    MethodInfo(String classAllName, String classSimpleName, String methodName, List<String> paramNames, Integer lineNumber) {
        this.classAllName = classAllName;
        this.classSimpleName = classSimpleName;
        this.methodName = methodName;
        this.paramNames = paramNames;
        this.lineNumber = lineNumber;
    }

    public String getClassAllName() {
        return this.classAllName;
    }

    public String getClassSimpleName() {
        return this.classSimpleName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public List<String> getParamNames() {
        return this.paramNames;
    }

    public Integer getLineNumber() {
        return this.lineNumber;
    }

    public void setClassAllName(final String classAllName) {
        this.classAllName = classAllName;
    }

    public void setClassSimpleName(final String classSimpleName) {
        this.classSimpleName = classSimpleName;
    }

    public void setMethodName(final String methodName) {
        this.methodName = methodName;
    }

    public void setParamNames(final List<String> paramNames) {
        this.paramNames = paramNames;
    }

    public void setLineNumber(final Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MethodInfo)) {
            return false;
        } else {
            MethodInfo other = (MethodInfo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$classAllName = this.getClassAllName();
                    Object other$classAllName = other.getClassAllName();
                    if (this$classAllName == null) {
                        if (other$classAllName == null) {
                            break label71;
                        }
                    } else if (this$classAllName.equals(other$classAllName)) {
                        break label71;
                    }

                    return false;
                }

                Object this$classSimpleName = this.getClassSimpleName();
                Object other$classSimpleName = other.getClassSimpleName();
                if (this$classSimpleName == null) {
                    if (other$classSimpleName != null) {
                        return false;
                    }
                } else if (!this$classSimpleName.equals(other$classSimpleName)) {
                    return false;
                }

                label57: {
                    Object this$methodName = this.getMethodName();
                    Object other$methodName = other.getMethodName();
                    if (this$methodName == null) {
                        if (other$methodName == null) {
                            break label57;
                        }
                    } else if (this$methodName.equals(other$methodName)) {
                        break label57;
                    }

                    return false;
                }

                Object this$paramNames = this.getParamNames();
                Object other$paramNames = other.getParamNames();
                if (this$paramNames == null) {
                    if (other$paramNames != null) {
                        return false;
                    }
                } else if (!this$paramNames.equals(other$paramNames)) {
                    return false;
                }

                Object this$lineNumber = this.getLineNumber();
                Object other$lineNumber = other.getLineNumber();
                if (this$lineNumber == null) {
                    if (other$lineNumber == null) {
                        return true;
                    }
                } else if (this$lineNumber.equals(other$lineNumber)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MethodInfo;
    }

    public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $classAllName = this.getClassAllName();
        int result = result * 59 + ($classAllName == null ? 43 : $classAllName.hashCode());
        Object $classSimpleName = this.getClassSimpleName();
        result = result * 59 + ($classSimpleName == null ? 43 : $classSimpleName.hashCode());
        Object $methodName = this.getMethodName();
        result = result * 59 + ($methodName == null ? 43 : $methodName.hashCode());
        Object $paramNames = this.getParamNames();
        result = result * 59 + ($paramNames == null ? 43 : $paramNames.hashCode());
        Object $lineNumber = this.getLineNumber();
        result = result * 59 + ($lineNumber == null ? 43 : $lineNumber.hashCode());
        return result;
    }

    public String toString() {
        return "MethodInfo(classAllName=" + this.getClassAllName() + ", classSimpleName=" + this.getClassSimpleName() + ", methodName=" + this.getMethodName() + ", paramNames=" + this.getParamNames() + ", lineNumber=" + this.getLineNumber() + ")";
    }
}

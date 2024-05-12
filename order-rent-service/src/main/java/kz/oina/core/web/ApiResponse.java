package kz.oina.core.web;

public record ApiResponse<T>(boolean success, String message, T data) {
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, data);
    }

    public static <T> ApiResponse<T> error(T data, String message) {
        return new ApiResponse<>(false, message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }


    public static <T> ApiResponse<T> error(T data) {
        return new ApiResponse<>(false, null, data);
    }
}
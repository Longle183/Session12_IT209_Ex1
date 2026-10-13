# Bài 1: CI Matrix Strategy cho Java Spring Boot Gradle

Dự án Java Spring Boot cấu hình quy trình CI tự động kiểm thử đa phiên bản JDK (JDK 17 và JDK 21) sử dụng **GitHub Actions Matrix Strategy** kết hợp với **Gradle Dependency Caching**.

---

## 1. Cấu trúc thư mục

```text
ex1/
├── .github/
│   └── workflows/
│       └── ci-matrix.yml         # GitHub Actions Workflow cấu hình Matrix Strategy
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   ├── main/java/com/example/demo/DemoApplication.java
│   └── test/java/com/example/demo/DemoApplicationTests.java
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

---

## 2. Chi tiết cấu hình Workflow (`.github/workflows/ci-matrix.yml`)

- **Trigger**: Kích hoạt khi có sự kiện `push` lên nhánh `main` (kèm `workflow_dispatch` để có thể kích hoạt thủ công qua GitHub UI).
- **Matrix Strategy**:
  - `strategy.matrix.java-version: ['17', '21']` sinh ra 2 sub-jobs song song:
    - `Test on Java 17`
    - `Test on Java 21`
- **Actions sử dụng**:
  - `actions/checkout@v5`: Kéo mã nguồn về runner.
  - `actions/setup-java@v5`:
    - Cài đặt JDK tương ứng theo biến `${{ matrix.java-version }}`.
    - Phân phối JDK: `distribution: 'temurin'`.
    - Bộ nhớ đệm dependencies: `cache: 'gradle'`.
  - Cấp quyền thực thi: `chmod +x gradlew`.
  - Thực thi kiểm thử: `./gradlew test`.

---

## 3. Các bước đẩy code lên GitHub & Kiểm tra

1. **Tạo commit trên nhánh main**:
   ```bash
   git add .
   git commit -m "feat: setup CI matrix strategy with JDK 17 and 21"
   ```

2. **Thêm GitHub Remote Repository và Push**:
   ```bash
   git remote add origin <URL_GITHUB_REPO_CỦA_BẠN>
   git push -u origin main
   ```

3. **Kiểm tra trên GitHub Actions**:
   - Truy cập vào repository trên GitHub -> chọn tab **Actions**.
   - Chọn workflow **Java CI Matrix Test**.
   - Quan sát job `test-matrix` tự động chia nhánh thành 2 sub-jobs chạy song song:
     - `test-matrix (17)` (hoặc `Test on Java 17`)
     - `test-matrix (21)` (hoặc `Test on Java 21`)
   - Cả 2 sub-jobs đều hoàn thành thành công với biểu tượng tích xanh (Green Checkmark).
   - Chụp ảnh màn hình hiển thị trực quan luồng Matrix Test để làm sản phẩm nộp bài.
   <img width="1917" height="921" alt="image" src="https://github.com/user-attachments/assets/5f2702ac-628a-461a-814e-63b4dc1dc0c7" />


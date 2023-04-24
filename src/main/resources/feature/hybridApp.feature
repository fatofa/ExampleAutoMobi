Feature: Demo Hybrid App
  Scenario Outline: Xem cac muc san pham khac nhau
    Given Nguoi dung mo app va di toi man hinh danh muc
    When Nguoi dung tab vao <danh muc>
    Then Nguoi dung se thay <danh muc con>
    Examples:
      | danh muc                   | danh muc con    |
      | Đồ Chơi - Mẹ & Bé          | Tã, Bỉm         |
      | Điện Thoại - Máy Tính Bảng | Điện thoại Smartphone   |
      | Điện Gia Dụng         | Đồ dùng nhà bếp |
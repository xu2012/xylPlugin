class ContactBean {
  String name;
  String phone;

  ContactBean({this.name, this.phone});

  factory ContactBean.fromMap(Map<dynamic, dynamic> map) =>
      new ContactBean(
        name: map["name"],
        phone: map["phone"],
      );
  Map<dynamic, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data["name"] = this.name;
    data["phone"] = this.phone;
    return data;
  }
}

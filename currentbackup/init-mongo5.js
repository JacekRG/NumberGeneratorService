db.auth("root", "example");
db.createUser(
    {
        user: "testowanko",
        pwd: "123456",
        roles: [{role: "userAdminAnyDatabase", db: "admin"}]
    }
)

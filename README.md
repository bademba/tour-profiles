# tour-profiles
This service handles user profiles which are meta data about a user eg avatar, phone number etc

1. **Create a profile**

**POST**
localhost:9195/v2/tour/users/user-profile

Request

``` 
{
    "fullName":"Jaime Lanister",
    "email":"jlanister@gmail.com",
    "phone":"+254720000010",
    "avatarUrl":"https://imgr.com/jlanister"
}
```

Response 200-OK

``` 
{
    "data": {
        "avatarUrl": "https://imgr.com/jlanister",
        "id": "2609644227",
        "phone": "+254720000010"
    },
    "message": "User profile created",
    "responseId": "96039856-347e-48d7-9fb1-366c4ca0dc2a",
    "status": 201,
    "timestamp": "08-09-2026 21:52:07"
}
```

2. **Fetch all profiles**

**GET** localhost:9195/v2/tour/users/user-profile
Response 200-OK

``` 
{
    "data": [
        {
            "avatarUrl": "https://imgr.com/avatar1",
            "id": "2609577062",
            "phone": "+254720000001"
        },
        {
            "avatarUrl": "https://imgr.com/jlanister",
            "id": "2609644227",
            "phone": "+254720000010"
        }
    ],
    "message": "User profile found",
    "responseId": "8cc468f3-edde-490c-a3f3-2af1a8c45ebd",
    "status": 200,
    "timestamp": "08-09-2026 21:53:11"
}
```
3. **Fetch a single user's profile**

**GET** localhost:9195/v2/tour/users/user-profile/jlanister@gmail.com

Response 200-OK
``` 
{
    "data": {
        "avatarUrl": "https://imgr.com/jlanister",
        "id": "2609644227",
        "phone": "+254720000010"
    },
    "message": "User profile  found",
    "responseId": "0beaf4f9-be5f-4e12-aff6-f0397abf3ea0",
    "status": 200,
    "timestamp": "08-09-2026 21:53:28"
}
```
4. **Update a user's profile**

**PUT** localhost:9195/v2/tour/users/user-profile/anne@gmail.com

Request

``` 
{
    "fullName":"Jaime Lanister",
    "email":"jlanister@gmail.com",
    "phone":"+254720000010",
    "avatarUrl":"https://imgr.com/jlanister.jpg"
}
```

Response 200-OK

``` 
{
    "data": {
        "avatarUrl": "https://imgr.com/jlanister.jpg",
        "id": "2609644227",
        "phone": "+254720000010"
    },
    "message": "User profile updated",
    "responseId": "f3fa23b3-5552-4646-8d7a-8edfe0aa3c87",
    "status": 200,
    "timestamp": "08-09-2026 21:57:40"
}
```

5. **Delete a profile**

**DELETE** localhost:9195/v2/tour/users/user-profile/jlanister@gmail.com

Response 204-No Content



Not the best code. Insufficient error handling.

Notes to self for learning GraphQL at [https://www.howtographql.com](https://www.howtographql.com), with necessary adjustments along the way.

#TODO: tidy up the client calls below
```
mutation signIn {
  signinUser(auth: {email: "smelly@c.at", password: "stink"}) {
    token
    user {
      id
      name
    }
  }
}

mutation createUser {
  createUser(
    name: "smelly"
    authProvider: {
      email: "smelly@c.at"
      password: "stink"}) {
    id
    name
  }
}


// Set header Authorization: Bearer 1
mutation link {
  createLink(
	  url: "https://smellycat.com"
  	  description: "cover your nose") {
      url
      description
  }
}

mutation vote {
  createVote(
    linkId: "3"
    userId: "1") {
    createdAt
    link { url }
    user { name }
  }
}


{
  allLinks(filter: {minimumVote: 0}, skip: 15, take: 5)
  {
    id
    url
    description
    votes
  }
}
```


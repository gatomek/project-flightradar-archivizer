# Setup

## Tower

## Prerequisites

* docker context `docker-on-tower` prepared on local machine with docker agent

```
docker context create docker-on-tower --docker host=tcp://tower:2375
```

### Setup DB

```sh
docker --context docker-on-tower compose --file remote-ansible.yml run --rm --build eventsaver-setupdb
```

### Allow remote access to DB

```sh
docker --context docker-on-tower compose --file remote-ansible.yml run --rm --build eventsaver-allowaccessdb
```


### Forbit remote access to DB

```sh
docker --context docker-on-tower compose --file remote-ansible.yml run --rm --build eventsaver-forbidaccessdb
```


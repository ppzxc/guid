[![Release](https://github.com/ppzxc/guid/actions/workflows/release.yml/badge.svg)](https://github.com/ppzxc/guid/actions/workflows/release.yml)

# id

- 구분자.
- RDBMS 에서 지원하는 AUTO INCREMENT 는 의미를 가질 수 없는 자동 증분 값.
- 샤딩이나 특별한 의미를 가지는 ID 생성을 위한 중앙 집중식 ID GENERATOR 는 유지보수 및 추가 비용이 발생.

# guid

- global, universal 의 단어 차이.
- GUID, UUID 는 일반적으로 동일한 의미로 쓰임.
- 다만 GUID 는 UUID 의 충돌 가능성을 없앤 유일값의 의미로도 쓰임.
- 모든 조건을 완벽하게 만족하는 GUID 는 없음.

# uuid

- UUID 는 글로벌 유니크 값으로 보지만 아주 극 소수의 충돌 가능성이 있음.
- 모든 시스템에서 동일하게 일반적인 형식으로 퍼져 있으므로 이식성이 좋아 보임.
- 최소한의 노력으로 사용 가능한 UUID 의 여러 버전이 있음.
- UUID 자체가 너무 큰 값이고 여차하면 String 으로 다뤄야 함.

# MongoDB ObjectId

- 12 byte.
- 16진수 문자열로 표현 가능한 ID.
- 정수형으로 표현 불가능

# custom guid 요구 사항

- 64bit, 8byte unsigned integer 로 표현 가능해야 한다.
- 각 INSTANCE 에서 생성 가능해야 한다.
- 정렬을 위해 시간 속성이 포함되어야 한다.
- 의미를 가질 수 있는 특별한 구분값이 있어야 한다.
- 자동 증분 값이 포함되어야 한다.

# Snowflake ID

```text
  0                   1                   2                   3
  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 |                          Timestamp                            |
 +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 |     Timestamp     |    Custom Id      |    Sequence Number    |
 +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
  0                   1                   2                   3
  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
```

- Timestamp 42 bit (ms 단위까지 표현 가능)
- Custom Id 10 bit
- Sequence Number 12 bit

# Instagram ID

```text
  0                   1                   2                   3
  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 |                          Timestamp                            |
 +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 |     Timestamp   |        Custom Id        |  Sequence Number  |
 +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
  0                   1                   2                   3
  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
```

- Timestamp 41 bit (ms 단위까지 표현 가능)
- Custom Id 13 bit (1024)
- Sequence Number 10 bit (1024)

# reference

- [Sharding & IDs at Instagram](https://instagram-engineering.com/sharding-ids-at-instagram-1cf5a71e5a5c)
- [Snowflake ID](https://en.wikipedia.org/wiki/Snowflake_ID)
- [https://github.com/beyondfengyu/SnowFlake](https://github.com/beyondfengyu/SnowFlake)
- [https://github.com/callicoder/java-snowflake](https://github.com/callicoder/java-snowflake)
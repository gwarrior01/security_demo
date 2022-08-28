build:
	docker build -t security_demo_jpa_postgres .

run:
	docker run --name security_demo_jpa_postgres --rm -d -p 5432:5432 security_demo_jpa_postgres
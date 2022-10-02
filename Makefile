build:
	docker build -t security_demo_2fa_postgres .

run:
	docker run --name security_demo_2fa_postgres --rm -d -p 5432:5432 security_demo_2fa_postgres
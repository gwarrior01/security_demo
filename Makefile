build:
	docker build -t security_demo_remember_me .

run:
	docker run --name security_demo_remember_me --rm -d -p 5432:5432 security_demo_remember_me
FROM registry.invalid/projects/ansible:latest
COPY . /project
WORKDIR /project/ansible
ENV ANSIBLE_CONFIG=/project/ansible/ansible.cfg

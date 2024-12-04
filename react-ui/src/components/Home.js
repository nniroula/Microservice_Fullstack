import React from 'react';
 
const Home = () => {
    return (
        <div>
            <h1 class="bg-success"> Spring Cloud Micro-Service Project...</h1>
            <div class="bg-primary">
                <h4 class="bg-warning">Backend tech stack include: </h4>
                <ul class="bg-warning">
                    <li class="text-danger">Java</li>
                    <li class="text-secondary">Spring Framework 6</li>
                    <li class="text-success">Spring Boot 3.3.5</li>
                    <li class="text-danger">Spring Cloud Micro-Service</li>
                    <li class="text-dark">Spring Data JPA</li>
                    <li class="text-primary">Spring Boot Starter Projects</li>
                    <li class="text-danger">Spring API Gateway</li>
                    <li class="text-primary">Eureka Service Registry Server</li>
                    <li class="text-secondary">Feign Client</li>

                    <li class="text-primary">SQL</li>
                    <li class="text-success">MYSQL RDBMS</li>

                    <li class="text-danger"> Other Technologies to be utilized:
                        <ul>
                            <li class="text-dark"> Zipkin Server</li>
                            <li class="text-dark"> Admin Server</li>
                            <li class="text-dark"> Config Server</li>
                            <li class="text-dark"> Resilience4j</li>
                        </ul>
                    </li>
                </ul>
            </div>

            <div class="bg-primary">
                <h4 class="bg-warning">Front-End tech stack include: </h4>
                <ul class="bg-warning">
                    <li class="text-primary">JavaScript</li>
                    <li class="text-danger">ReactJS</li>
                    <li class="text-secondary">BootStrap</li>
                    <li class="text-success">Axios</li>
                    <li class="text-secondary">HTML</li>
                    <li class="text-primary">CSS</li>
                </ul>
            </div>
        </div>
    )
}

export default Home;
import React from 'react';
import '../styles/home.css';
import { Layout } from '../components/Layout';
import { HeroSection } from '../components/HeroSection';
import { AboutSection } from '../components/AboutSection';
import { TestimonySection } from '../components/TestimonySection';

const Home = () => {


    return(
        <Layout>
            <HeroSection/>
            <AboutSection/>
            <TestimonySection/>
        </Layout>
    );

}

export default Home;
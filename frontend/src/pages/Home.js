import React from 'react';
import '../styles/home.css';
import '../styles/text.css';
import { Layout } from '../components/Layout';
import { HeroSection } from '../components/HeroSection';
import { AboutSection } from '../components/AboutSection';
import { TestimonySection } from '../components/TestimonySection';

export const Home = () => {


    return(
        <Layout>
            <HeroSection/>
            <AboutSection/>
            <TestimonySection/>
        </Layout>
    );

}
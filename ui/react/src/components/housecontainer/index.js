import React, {Component} from "react";
import axios from "axios";

import {Container, List, ListItem} from '@material-ui/core';

import House from "../house";

const baseUrl = "/api/house";

function ListItemLink(props) {
    return <ListItem button component="a" {...props} />;
}

class HouseContainer extends Component {

    constructor(props) {
        super(props);

        this.state = {
            houses: null
        };

        this.fetchHouses();
    }

    fetchHouses = () => {
        axios({
            method: "GET",
            url: `${baseUrl}`,
        }).then(response => {
                console.log("hier")
                this.setState({houses: response.data})
            }
        );
    };

    render() {
        const {houses} = this.state;
        return (
            <Container maxWidth="sm">
                <List component="nav" aria-label="secondary mailbox folders">
                    {

                        houses && houses.length ? houses.map(house => {
                            return (
                                <ListItemLink style={{display: "block"}}
                                              href={house.url}
                                              button>
                                    <House data={house}/>
                                </ListItemLink>)
                        }) : <p>No houses</p>
                    }
                </List>
            </Container>
        );
    };
}


export default HouseContainer;
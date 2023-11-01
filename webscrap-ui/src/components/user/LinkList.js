import React from 'react'
import { Grid, Header, Form, Icon, Image, Input, Item, Segment } from 'semantic-ui-react'

function LinkList({ isLinksLoading, linkTextSearch, links, handleInputChange, handleSearchLink }) {
  let linkList
  if (links.length === 0) {
    linkList = <Item key='no-link'>No Links</Item>
  } else {
    linkList = links.map(link => {
      return (
        <Item key={link.id}>
          <Item.Content>
            <Item.Header>{link.url}</Item.Header>
            <Item.Meta>{link.counter}</Item.Meta>
            <Item.Description>{link.description}</Item.Description>

          </Item.Content>
        </Item>
      )
    })
  }

  return (
    <Segment loading={isLinksLoading} color='blue'>
      <Grid stackable divided>
        <Grid.Row columns='2'>
          <Grid.Column width='3'>
            <Header as='h2'>
              <Icon name='linkify' />
              <Header.Content>Links</Header.Content>
            </Header>
          </Grid.Column>
          <Grid.Column>
            <Form onSubmit={handleSearchLink}>
              <Input
                action={{ icon: 'search' }}
                name='linkTextSearch'
                placeholder='Search by url or description'
                value={linkTextSearch}
                onChange={handleInputChange}
              />
            </Form>
          </Grid.Column>
        </Grid.Row>
      </Grid>
      <Item.Group divided unstackable relaxed link>
        {linkList}
      </Item.Group>
    </Segment>
  )
}

export default LinkList